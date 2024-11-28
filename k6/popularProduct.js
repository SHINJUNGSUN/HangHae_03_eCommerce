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

    const params = {
        headers: { 'Content-Type': 'application/json', }
    }

    const response = http.get(`${DEFAULT_URL}/api/products/popular`, null, params);
    check(response, { '상위 상품 조회': (res) => res.status === 200 });

    sleep(1);
}