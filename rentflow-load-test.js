import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {

    vus: 100,

    duration: '30s',

    thresholds: {

        http_req_duration: [

            'p(90)<500',
            'p(95)<800',
            'p(99)<1500',
        ],

        http_req_failed: ['rate<0.01'],
    },
};

const BASE_URL = 'http://192.168.0.108:8080';

export default function () {

    
    let buildings = http.get(`${BASE_URL}/buildings`);

    check(buildings, {
        'GET /buildings status 200': (r) => r.status === 200,
    });

    
    let buildingDetail = http.get(
        `${BASE_URL}/buildings/75509c81-a5a6-409f-a7ab-11ddfae49ddd`
    );

    check(buildingDetail, {
        'GET /buildings/{id} status 200': (r) => r.status === 200,
    });

    
    let units = http.get(
        `${BASE_URL}/buildings/75509c81-a5a6-409f-a7ab-11ddfae49ddd/units`
    );

    check(units, {
        'GET /units status 200': (r) => r.status === 200,
    });

    
    let tenant = http.get(
        `${BASE_URL}/tenants/4df4b434-616f-45ad-9644-14bf687bc005`
    );

    check(tenant, {
        'GET /tenant status 200': (r) => r.status === 200,
    });

    
    let tenantCharges = http.get(
        `${BASE_URL}/charge/tenant/4df4b434-616f-45ad-9644-14bf687bc005`
    );

    check(tenantCharges, {
        'GET /charge/tenant status 200': (r) => r.status === 200,
    });

    
    let templates = http.get(
        `${BASE_URL}/charge-templates`
    );

    check(templates, {
        'GET /charge-templates status 200': (r) => r.status === 200,
    });

    sleep(1);
}