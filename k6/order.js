import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 1000 },
        { duration: '20s', target: 2000 },
        { duration: '30s', target: 3000 },
        { duration: '40s', target: 2000 },
        { duration: '50s', target: 1000 },
    ],
}

export default function () {

    const DEFAULT_URL = 'http://localhost:8080';

    let params = {
        headers: { 'Content-Type': 'application/json', }
    }

    const login = http.post(`${DEFAULT_URL}/api/users/login`, JSON.stringify({
        userId: 'sjs818',
        password: '1q2w3e4r!!'
    }), params);

    check(login, { '로그인': (res) => res.status === 200 });

    const body = JSON.parse(login.body);

    params = {
        headers: {
            'Authorization': `Bearer ${body.token}`,
            'Content-Type': 'application/json'
        }
    }

    const payload = JSON.stringify({
        OrderProductList: [
            {
                productId: 1,
                quantity: 1
            }
        ]
    });

    const response = http.post(`${DEFAULT_URL}/api/orders`, payload, params);
    check(response, { '주문': (res) => res.status === 200 });

    sleep(1);
}