import React, { useState } from 'react';
import { viewArchivedData } from '../api';

function ArchiveViewer({ token }) {
  const [table, setTable] = useState('');
  const [data, setData] = useState([]);

  const handleFetch = () => {
    viewArchivedData(table, token).then(setData);
  };

  return (
    <div>
      <h3>View Archived Data</h3>
      <input placeholder="Table name (e.g., student)" value={table} onChange={(e) => setTable(e.target.value)} />
      <button onClick={handleFetch}>Fetch</button>
      <pre>{JSON.stringify(data, null, 2)}</pre>
    </div>
  );
}

export default ArchiveViewer;
