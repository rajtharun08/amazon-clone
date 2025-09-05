import api from './api';

const getAllProducts = () => {
    // The 'page' and 'size' params can be added later for pagination
    return api.get('/products'); 
};

const getProductById = (id) => {
    return api.get(`/products/${id}`);
};

const productService = {
    getAllProducts,
    getProductById,
};

export default productService;