import './App.css'
import ProductCard from './ProductCard'

function App() {
  
  // 1. THE BRAINS: We create a JS Array [ ] holding our database items { }
  // (Soon, we will fetch this exact array directly from your Java MySQL database!)
  const inventory = [
    { id: 1, name: "Mk-1 Pro Keyboard", price: 99.99, stock: 3, category: "Hardware" },
    { id: 2, name: "Neon Mouse", price: 49.99, stock: 15, category: "Peripherals" },
    { id: 3, name: "Desk Mat", price: 19.99, stock: 50, category: "Accessories" },
    { id:4, name: "GigaByte CPU", price:84.99, stock:40, category:"Hardware"}
  ];

  // 2. THE FACE: We use the { } portal to run JS inside our HTML
  return (
    <div>
      <h1>Neon Mech Premium</h1>
      
      {/* 3. THE CONVEYOR BELT: We map over the array. 
          For every 'item' in the list, we return one ProductCard! */}
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