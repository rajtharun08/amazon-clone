import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchCart } from '../redux/cartSlice';
import { Link } from 'react-router-dom';

const CartPage = () => {
    const dispatch = useDispatch();
    const { items, status } = useSelector((state) => state.cart);

    useEffect(() => {
        // Fetch the cart from the backend when the component loads
        if (status === 'idle') {
            dispatch(fetchCart());
        }
    }, [status, dispatch]);

    const totalAmount = items.reduce((total, item) => 
        total + item.price * item.quantity, 0
    ).toFixed(2);

    if (status === 'loading') return <p>Loading cart...</p>;

    return (
        <div style={{ padding: '20px' }}>
            <h1>Your Shopping Cart</h1>
            {items.length === 0 ? (
                <p>Your cart is empty. <Link to="/">Go Shopping</Link></p>
            ) : (
                <div>
                    {items.map(item => (
                        <div key={item.id} style={{ display: 'flex', justifyContent: 'space-between', borderBottom: '1px solid #ddd', padding: '10px 0' }}>
                            <span>{item.productName}</span>
                            <span>{item.quantity} x ${item.price.toFixed(2)}</span>
                        </div>
                    ))}
                    <h3 style={{ textAlign: 'right', marginTop: '20px' }}>Total: ${totalAmount}</h3>
                    <button style={{ float: 'right', padding: '10px 20px', background: '#f0c14b', border: '1px solid #a88734' }}>
                        Proceed to Checkout
                    </button>
                </div>
            )}
        </div>
    );
};

export default CartPage;