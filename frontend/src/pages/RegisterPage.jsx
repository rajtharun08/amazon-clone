import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
    });
    const [error, setError] = useState('');
    const { register } = useAuth();
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        const result = await register({ ...formData, role: 'USER' });
        if (result.success) {
            navigate('/');
        } else {
            setError(result.message || 'Failed to register.');
        }
    };

    return (
        <div style={{ maxWidth: '400px', margin: '2rem auto', padding: '2rem', border: '1px solid #ddd', borderRadius: '4px' }}>
            <form onSubmit={handleSubmit}>
                <h2>Register</h2>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <div style={{ marginBottom: '1rem' }}>
                    <label>First Name</label>
                    <input type="text" name="firstName" onChange={handleChange} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
                </div>
                <div style={{ marginBottom: '1rem' }}>
                    <label>Last Name</label>
                    <input type="text" name="lastName" onChange={handleChange} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
                </div>
                <div style={{ marginBottom: '1rem' }}>
                    <label>Email</label>
                    <input type="email" name="email" onChange={handleChange} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
                </div>
                <div style={{ marginBottom: '1rem' }}>
                    <label>Password</label>
                    <input type="password" name="password" onChange={handleChange} required style={{ width: '100%', padding: '8px', boxSizing: 'border-box' }} />
                </div>
                <button type="submit" style={{ width: '100%', padding: '10px', background: '#f0c14b', border: '1px solid #a88734', cursor: 'pointer' }}>Register</button>
                 <p style={{ textAlign: 'center', marginTop: '1rem' }}>
                    Already have an account? <Link to="/login">Sign in</Link>
                </p>
            </form>
        </div>
    );
};

export default RegisterPage;