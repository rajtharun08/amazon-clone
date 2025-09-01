import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const PrivateRoute = () => {
    const { user } = useAuth();
    
    // If the user is authenticated, render the child route. Otherwise, redirect to login.
    return user ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoute;