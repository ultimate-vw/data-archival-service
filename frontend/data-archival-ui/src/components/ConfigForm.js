import React, { useState, useEffect } from 'react';
import { saveConfig, getConfigs, runArchive, runDelete } from '../api';

function ConfigForm({ token }) {
  const [config, setConfig] = useState({
    tableName: '',
    criteriaColumn: '',
    archiveAfterMonths: 6,
    deleteAfterMonths: 24,
    pageSize: 50,
  });

  const [configs, setConfigs] = useState([]);

  const fetchConfigs = async () => {
    const res = await getConfigs(token);
    setConfigs(res);
  };

  useEffect(() => {
    fetchConfigs();
  }, []);

  const handleChange = e => setConfig({ ...config, [e.target.name]: e.target.value });

  const handleSubmit = e => {
    e.preventDefault();
    saveConfig(config, token);
    fetchConfigs();
  };

  return (
    <div>
      <h3>Config Form</h3>
      <form onSubmit={handleSubmit}>
        <input name="tableName" onChange={handleChange} placeholder="Table Name" />
        <input name="criteriaColumn" onChange={handleChange} placeholder="Criteria Column" />
        <input name="archiveAfterMonths" onChange={handleChange} placeholder="Archive After Months" />
        <input name="deleteAfterMonths" onChange={handleChange} placeholder="Delete After Months" />
        <input name="pageSize" onChange={handleChange} placeholder="Page Size" />
        <button type="submit">Save Config</button>
      </form>
      <button onClick={() => runArchive(token)}>Run Archival</button>
      <button onClick={() => runDelete({ sourceTable: 'student', cutoffDate: '2022-01-01', pageSize: 50 }, token)}>Run Delete</button>
      <h4>Configs</h4>
      <pre>{JSON.stringify(configs, null, 2)}</pre>
    </div>
  );
}

export default ConfigForm;
