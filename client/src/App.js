import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react'

function App() {

  const [list, setList] = useState([]);
  const [newTask, setNewTask] = useState({taskname: "", description: ""})

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

  const handleNewTask = (e) =>{

    const updatedTask = {...newTask, taskname: e.target.value}

setNewTask(updatedTask);
  }

  const handleNewDescription = (e) =>{

    const updatedTask = {...newTask, description: e.target.value}
   
  
  setNewTask(updatedTask);
    }

    const handleAddTask = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/todolists', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            taskname: newTask.taskname,
            description: newTask.description
          })
        });
  
        if (!response.ok) {
          throw new Error('Failed to add task');
        }
  
        const jsonData = await response.json();
  
        const updatedTask = {
          ...jsonData, // Assuming the response contains the newly added task with an ID
          completed: true
        };
  
        const newList = [...list, updatedTask];
        setList(newList);
      } catch (error) {
        console.error('Error adding task:', error);
      }
    }
  


  
  return (
   <div>
    <h1><center> To do list: </center></h1>
    <div>
      <label>Enter New Taskname: </label>
      <input type="text" name="task" value={newTask.taskname} onChange={handleNewTask}/>
      <label>Enter Description: </label>
      <input type="text" name="description" value={newTask.description} onChange={handleNewDescription}/>
      <button onClick={handleAddTask}>
        Add Task
      </button>
    </div>
    <ul>
    {list.map((item)=>(
<li key={item.id}><p>{item.taskname}</p> <p>{item.description}</p></li>
    ))}
    </ul>
   </div>
  );
}

export default App;
