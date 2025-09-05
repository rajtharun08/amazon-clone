import React from 'react';
import cartService from '../services/cartService';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const ProductCard = ({ product }) => {
    const { user } = useAuth();
    const navigate = useNavigate();

    const handleAddToCart = async () => {
        if (!user) {
            // If the user is not logged in, redirect them to the login page
            navigate('/login');
            return;
        }

        try {
            await cartService.addToCart(product.id, 1);
            alert(`${product.name} has been added to your cart!`);
        } catch (error) {
            console.error("Failed to add to cart:", error);
            alert("There was an error adding the item to your cart.");
        }
    };

    return (
        <div style={{ border: '1px solid #ddd', padding: '1rem', borderRadius: '8px', textAlign: 'center', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
            <img 
                src={product.imageUrl || 'https://via.placeholder.com/200'} 
                alt={product.name} 
                style={{ width: '100%', height: '200px', objectFit: 'contain' }} 
            />
            <div>
                <h3 style={{ fontSize: '1rem', margin: '0.5rem 0' }}>{product.name}</h3>
                <p style={{ fontWeight: 'bold', fontSize: '1.2rem', margin: '0.5rem 0' }}>${product.price}</p>
                <button 
                    onClick={handleAddToCart} 
                    style={{ 
                        width: '100%', 
                        padding: '10px', 
                        background: '#f0c14b', 
                        border: '1px solid #a88734', 
                        borderRadius: '4px',
                        cursor: 'pointer',
                        marginTop: 'auto'
                    }}
                >
                    Add to Cart
                </button>
            </div>
        </div>
    );
};

export default ProductCard;