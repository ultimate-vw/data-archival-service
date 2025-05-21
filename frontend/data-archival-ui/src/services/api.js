import axios from 'axios';

const BASE = 'http://44.211.192.140:8080/data-archival-service/api';

//const BASE = process.env.REACT_APP_API_BASE;

export const register = (data) =>
  fetch(`${BASE}/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });

export const login = async (data) => {
  const res = await fetch(`${BASE}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  return await res.json();
};

export const saveConfig = (data, token) =>
  fetch(`${BASE}/archival/config`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

export const getConfigs = (token) =>
  fetch(`${BASE}/archival/config`, {
    headers: { Authorization: `Bearer ${token}` },
  }).then((res) => res.json());

export const runArchive = (token) =>
  fetch(`${BASE}/archival/run`, {
    method: 'POST',
    headers: { Authorization: `Bearer ${token}` },
  });

export const runDelete = (data, token) =>
  fetch(`${BASE}/archival/delete`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

export const viewArchivedData = (table, token) =>
  fetch(`${BASE}/archival/view/${table}`, {
    headers: { Authorization: `Bearer ${token}` },
  }).then((res) => res.json());
