import React, { useState } from 'react';
import { register } from '../api';

function RegisterForm() {
  const [form, setForm] = useState({ username: '', password: '', roles: 'User' });

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = e => {
    e.preventDefault();
    register(form);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      <input name="username" onChange={handleChange} placeholder="Username" />
      <input name="password" type="password" onChange={handleChange} placeholder="Password" />
      <select name="roles" onChange={handleChange}>
        <option value="User">User</option>
        <option value="Admin">Admin</option>
        <option value="Student">Student</option>
        <option value="Teacher">Teacher</option>
      </select>
      <button type="submit">Register</button>
    </form>
  );
}

export default RegisterForm;
