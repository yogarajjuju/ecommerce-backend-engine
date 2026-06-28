// 1. Put the word 'props' in the parentheses to catch the incoming data!
function ProductCard(props) {
  
  // Notice we deleted the hardcoded variables in the Brains! 
  // We don't need them anymore because the data is coming from the outside.

  return (
    <div style={{ border: '1px solid #a855f7', padding: '20px', borderRadius: '10px', margin: '10px' }}>
      
      {/* 2. We access the object properties just like we did in the JS Playground! */}
      <h2>{props.name}</h2>
      <p>Price: ${props.price}</p>
      <p style={{ color: '#a855f7' }}>Stock: {props.stock}</p>
      <p style={{color: '#a855f7'}}>Category: {props.category}</p>
      {/* <pstyle={{color: '#a855f7'}}>Inventory: {props.inventory}</p> */}
      
      <button onClick={() => alert("Purchasing: " + props.name)}>
        Initialize Purchase
      </button>

    </div>
  );
}

export default ProductCard;