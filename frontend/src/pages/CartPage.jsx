import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchCart } from '../redux/cartSlice';
import { Link, useNavigate } from 'react-router-dom';
import orderService from '../services/orderService'; // We will create this next

const CartPage = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { items, status } = useSelector((state) => state.cart);
    const { user } = useAuth(); // Assuming useAuth is available globally

    useEffect(() => {
        // Fetch the cart from the backend only if the user is logged in
        if (user && status === 'idle') {
            dispatch(fetchCart());
        }
    }, [status, dispatch, user]);

    const totalAmount = items.reduce((total, item) => 
        total + item.price * item.quantity, 0
    ).toFixed(2);

    const handleCheckout = async () => {
        try {
            await orderService.placeOrder();
            alert("Order placed successfully!");
            dispatch(fetchCart()); // Refresh the cart (it should be empty)
            navigate('/orders'); // Redirect to orders page
        } catch (error) {
            console.error("Failed to place order:", error);
            alert("There was an error placing your order.");
        }
    };
    
    if (status === 'loading') return <p>Loading cart...</p>;

    return (
        <div style={{ padding: '20px', maxWidth: '800px', margin: 'auto' }}>
            <h1>Your Shopping Cart</h1>
            {items.length === 0 ? (
                <p>Your cart is empty. <Link to="/">Go Shopping</Link></p>
            ) : (
                <div>
                    {items.map(item => (
                        <div key={item.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', borderBottom: '1px solid #ddd', padding: '10px 0' }}>
                            <span style={{ flex: 2 }}>{item.productName}</span>
                            <span style={{ flex: 1 }}>Qty: {item.quantity}</span>
                            <span style={{ flex: 1, textAlign: 'right' }}>${(item.price * item.quantity).toFixed(2)}</span>
                        </div>
                    ))}
                    <h3 style={{ textAlign: 'right', marginTop: '20px' }}>Total: ${totalAmount}</h3>
                    <button 
                        onClick={handleCheckout}
                        style={{ float: 'right', padding: '10px 20px', background: '#f0c14b', border: '1px solid #a88734', cursor: 'pointer', fontSize: '1rem' }}>
                        Proceed to Checkout
                    </button>
                </div>
            )}
        </div>
    );
};

export default CartPage;