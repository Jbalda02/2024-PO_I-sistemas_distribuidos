import grpc from '@grpc/grpc-js'
import protoLoder from '@grpc/proto-loader'

const PROTO_FILE = './proto/user.proto'

async function main() {
    const package_definition = protoLoder.loadSync(
        PROTO_FILE,
        {keepCase:true, longs:String, enums:String, arrays:true}
    )
    
    const user_proto = grpc.loadPackageDefinition(package_definition)
    const server = new grpc.Server()

    let users = []

    server.addService(user_proto.UserService.service, {
        getUsers: (_, callback) => {
            callback(null, { Users: [ users ] })
        },
        addUser: (call, callback) => {
            const user = call.request
            users.push(user)
            callback(null, user)
        }
    })

    await server.bindAsync(
        '127.0.0.1:3030',
        grpc.ServerCredentials.createInsecure(),
        () => {
            server.start()
            console.log('Server RPC on port 3030')
        }
    )
}
