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
          completed: false
        };
  
        const newList = [...list, updatedTask];
        setList(newList);
        setNewTask({ taskname: "", description: ""}); // resets input fields after adding tasks
      } catch (error) {
        console.error('Error adding task:', error);
      }
    }

    const deleteAllTasks  = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/todolists',{
        method: 'DELETE'
      
    });

    if (!response.ok){
      throw new Error('failed to delete all tasks')
    }

      setList([]);
    } catch(error) {
      console.error('Error deleting all tasks:', error);
    }
  
  }


  const deleteTaskById = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/api/todolists/${id}`,{
      method: 'DELETE'
    });

    if (!response.ok) {
      throw new Error('failed to delete task');
    }

    const updatedList = list.filter(task => task.id !== id)
    setList(updatedList);
  } catch (error)  {
    console.error('Error deleting task:', error);
  }

};


const markTaskAsComplete = async (id) => {
  try {
    const response = await fetch(`http://localhost:8080/api/todolists/${id}/markComplete`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    if(!response.ok) {
      throw new Error('failed to mark task as complete');
    }

    // update the local state to reflect the change
    const updatedList = list.map(task =>
      task.id === id ? {...task, completed: true } : task
    );
    setList(updatedList);
  } catch (error) {
    console.error('Error marking task as complete:', error);
  }
};



  
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
      
      <button onClick={deleteAllTasks}>
        Delete All Tasks
      </button>
    </div>
    <ul>
    {list.map((item)=>(
<li key={item.id}>
<p>{item.taskname}</p> 
<p>{item.description}</p>
<p>Completed: {item.completed ? "Yes" : "No" }</p>
{!item.completed && (
  <button onClick={() => markTaskAsComplete(item.id)}>
    Mark as Complete
  </button>
)}
<button onClick={() => deleteTaskById(item.id)}>
  Delete Task
</button>
</li>
    ))}
    </ul>
   </div>
  );
}

export default App;
