import api from './api';

const addToCart = (productId, quantity) => {
    return api.post(`/cart/add?productId=${productId}&quantity=${quantity}`);
};

const getCart = () => {
    return api.get('/cart');
};

const cartService = {
    addToCart,
    getCart,
};

export default cartService;