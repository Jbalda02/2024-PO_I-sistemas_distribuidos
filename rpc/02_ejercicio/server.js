const grpc = require('@grpc/grpc-js')
const protoLoder = require('@grpc/proto-loader')
const mariadb = require('mariadb')

const PROTO_FILE = './proto/user.proto'

async function main() {
    const package_definition = protoLoder.loadSync(
        PROTO_FILE,
        {keepCase:true, longs:String, enums:String, arrays:true}
    )

    const user_proto = grpc.loadPackageDefinition(package_definition).UserService
    const server = new grpc.Server()

    const pool = mariadb.createPool({
        host: 'localhost',
        user: 'root',
        password: '1234',
        database: 'user_db'
    })

    let users = []

    async function getUsersFromDatabase() {
        let conn;
        try {
            conn = await pool.getConnection();
            const rows = await conn.query('SELECT * FROM users');
            users = rows;
        } catch (err) {
            console.error(err);
        } finally {
            if (conn) conn.release(); // release to pool
        }
    }

    getUsersFromDatabase().catch(console.error);

    server.addService(user_proto.service, {
        getUsers: (_, callback) => {
            console.log(users)
            callback(null, { users })
        },
        addUser: (call, callback) => {
            const user = call.request
            console.log(user)
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

main()