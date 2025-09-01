import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import api from '../services/api';

// Async thunk to fetch the user's cart from the backend
export const fetchCart = createAsyncThunk('cart/fetchCart', async () => {
  const response = await api.get('/cart');
  return response.data;
});

// Async thunk to add an item to the cart
export const addToCart = createAsyncThunk('cart/addToCart', async ({ productId, quantity }) => {
  const response = await api.post(`/cart/add?productId=${productId}&quantity=${quantity}`);
  return response.data;
});

const cartSlice = createSlice({
  name: 'cart',
  initialState: {
    items: [],
    status: 'idle', // 'idle' | 'loading' | 'succeeded' | 'failed'
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchCart.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchCart.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.items = action.payload;
      })
      .addCase(fetchCart.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(addToCart.fulfilled, (state, action) => {
        // Logic to add or update item in cart state
        const newItem = action.payload;
        const existingItem = state.items.find(item => item.productId === newItem.productId);
        if (existingItem) {
          existingItem.quantity = newItem.quantity;
        } else {
          state.items.push(newItem);
        }
      });
  },
});

export default cartSlice.reducer;