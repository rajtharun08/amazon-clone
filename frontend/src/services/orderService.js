import api from './api';

const placeOrder = () => {
    return api.post('/orders/place');
};

const getOrders = () => {
    return api.get('/orders');
};

const orderService = {
    placeOrder,
    getOrders,
};

export default orderService;