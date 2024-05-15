import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react'

function App() {

  const [list, setList] = useState([]);

  useEffect(()=>{
    const fetchData = async () => {
      try {
        // Make a fetch request to your API endpoint
        const response = await fetch('http://localhost:8080/api/todolists');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const jsonData = await response.json();
        setList(jsonData);
        
      } catch (error) {
        console.error('Error fetching data:', error);
        
      }
    };

    fetchData();
  }, [])
  return (
   <div>
    <h1>To do list: </h1>
    <ul>
    {list.map((item)=>(
<li key={item.id}><p>{item.taskname}</p></li>
    ))}
    </ul>
   </div>
  );
}

export default App;
