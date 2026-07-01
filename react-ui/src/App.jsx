import { useState, useEffect } from 'react' // 1. Import the React Hooks!
import './App.css'
import ProductCard from './ProductCard'

function App() {
  
  // 2. THE MEMORY BANK: 
  // 'inventory' is the variable holding the data. 
  // 'setInventory' is the ONLY function allowed to update that data.
  // We start it off as an empty array [ ] because the database hasn't answered yet!
  const [inventory, setInventory] = useState([]);

  // 3. THE AUTO-TRIGGER:
  // This block of code runs exactly ONE TIME when the page first loads.
  useEffect(() => {
    
    // Remember Rule 5 (Async/Await)? Here it is in action!
    const fetchDatabase = async () => {
      try {
        console.log("📡 Pinging Java Server...");
        
        // Change '/products' to whatever your actual Java GET endpoint is!
        // const response = await fetch('http://localhost:8080/products'); 
       // Notice it is HTTPS now, and uses your GitHub Codespace URL!
       // Ensure it looks EXACTLY like this!
const response = await fetch('https://jubilant-system-6p469j5xqgrhrqr7-8080.app.github.dev/products');

        const data = await response.json();
        
        console.log("✅ Data received from MySQL:", data);
        
        // Store the incoming data into our React Memory Bank!
        setInventory(data); 
        
      } catch (error) {
        console.error("Sniper! Connection failed:", error);
      }
    };

    fetchDatabase(); // Pull the trigger!
    
  }, []); // <-- This empty array tells React: "Only do this ONCE."

  
  // 4. THE FACE
  return (
    <div>
      <h1>Neon Mech Premium</h1>
      
      {/* If the array is empty (waiting for Java), show a loading message */}
      {inventory.length === 0 ? <p>Loading database...</p> : null}

      {/* The exact same map function from yesterday! */}
      {inventory.map(item => (
        <ProductCard 
          key={item.id} 
          name={item.name} 
          price={item.price} 
          stock={item.stock} 
          category={item.category} 
        />
      ))}
      
    </div>
  )
}

export default App