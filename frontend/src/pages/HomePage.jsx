import React from 'react';
import { useAuth } from '../context/AuthContext';

const HomePage = () => {
  const { user } = useAuth();
  return (
    <div>
      <h1>Welcome to the Amazon Clone</h1>
      {user ? (
        <p>You are logged in as {user.email}. Start shopping!</p>
      ) : (
        <p>Please log in or register to continue.</p>
      )}
    </div>
  );
};

export default HomePage;