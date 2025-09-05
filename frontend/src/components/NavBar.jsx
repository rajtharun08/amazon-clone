import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useSelector, useDispatch } from 'react-redux';
import { fetchCart } from '../redux/cartSlice';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const cartItems = useSelector((state) => state.cart.items);

  // Fetch the cart as soon as the user logs in
  useEffect(() => {
    if (user) {
      dispatch(fetchCart());
    }
  }, [user, dispatch]);

  const handleLogout = () => {
    logout();
    // We might want to clear the cart state on logout as well
    navigate('/login');
  };

  const cartItemCount = cartItems.reduce((total, item) => total + item.quantity, 0);

  return (
    <nav style={{ display: 'flex', justifyContent: 'space-between', padding: '1rem', background: '#232f3e', color: 'white', alignItems: 'center' }}>
      <Link to="/" style={{ color: 'white', textDecoration: 'none', fontSize: '1.5rem' }}>
        Amazon Clone
      </Link>
      <div style={{ display: 'flex', gap: '1.5rem', alignItems: 'center' }}>
        {user ? (
          <>
            <span>Welcome, {user.email}</span>
            <Link to="/orders" style={{ color: 'white', textDecoration: 'none' }}>My Orders</Link>
            <Link to="/cart" style={{ color: 'white', textDecoration: 'none' }}>
              Cart ({cartItemCount})
            </Link>
            <button onClick={handleLogout} style={{ background: '#febd69', border: 'none', padding: '0.5rem 1rem', cursor: 'pointer' }}>Logout</button>
          </>
        ) : (
          <>
            <Link to="/login" style={{ color: 'white', textDecoration: 'none' }}>Login</Link>
            <Link to="/register" style={{ color: 'white', textDecoration: 'none' }}>Register</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;