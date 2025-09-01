import React from 'react';
import { useDispatch } from 'react-redux';
import { addToCart } from '../redux/cartSlice';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';


const ProductCard = ({ product }) => {
    const dispatch = useDispatch();
    const { user } = useAuth();
    const navigate = useNavigate();

    const handleAddToCart = () => {
        if (!user) {
            navigate('/login'); // Redirect to login if not authenticated
            return;
        }
        dispatch(addToCart({ productId: product.id, quantity: 1 }));
        alert(`${product.name} added to cart!`);
    };

    return (
        <div style={{ border: '1px solid #ddd', padding: '1rem', borderRadius: '8px', textAlign: 'center' }}>
            <img src={product.imageUrl || 'https://via.placeholder.com/150'} alt={product.name} style={{ maxWidth: '100%', height: '150px', objectFit: 'cover' }} />
            <h3>{product.name}</h3>
            <p>${product.price}</p>
            <button onClick={handleAddToCart} style={{ background: '#f0c14b', border: '1px solid #a88734', padding: '0.5rem 1rem', cursor: 'pointer' }}>
                Add to Cart
            </button>
        </div>
    );
};

export default ProductCard;