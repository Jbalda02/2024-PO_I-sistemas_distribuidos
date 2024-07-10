const chai = require('chai');
const chaiHttp = require('chai-http');
const expect = chai.expect;
chai.use(chaiHttp);

const app = require('./main'); // Assuming your main file is named 'main.js'

describe('GET /', () => {
    it('should return an array of users', (done) => {
        chai.request(app)
            .get('/')
            .end((err, res) => {
                expect(res).to.have.status(200);
                expect(res.body).to.be.an('array');
                done();
            });
    });

    it('should handle errors and return status 500', (done) => {
        chai.request(app)
            .get('/')
            .end((err, res) => {
                expect(res).to.have.status(500);
                expect(res.body).to.have.property('msg');
                done();
            });
    });
});

describe('POST /add', () => {
    it('should add a new user and return success message', (done) => {
        const user = {
            name: 'John Doe',
            age: 25,
            email: 'john.doe@example.com'
        };

        chai.request(app)
            .post('/add')
            .send(user)
            .end((err, res) => {
                expect(res).to.have.status(200);
                expect(res.body).to.have.property('msg');
                expect(res.body).to.have.property('user');
                done();
            });
    });

    it('should handle errors and return status 500', (done) => {
        const user = {
            name: 'John Doe',
            age: 25,
            email: 'john.doe@example.com'
        };

        chai.request(app)
            .post('/add')
            .send(user)
            .end((err, res) => {
                expect(res).to.have.status(500);
                expect(res.body).to.have.property('msg');
                done();
            });
    });
});