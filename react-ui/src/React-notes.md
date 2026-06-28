# React Basics: The UI Engine

## 1. Components (The Lego Bricks)
* Instead of writing one giant HTML file, we break the UI into reusable chunks called Components (e.g., `ProductCard.jsx`).
* Components MUST start with a Capital Letter.
* **JSX:** React lets you write HTML directly inside your JavaScript.

## 2. The Anatomy of a Component
* **The Brains (Zone 1):** Normal JavaScript (variables, math, data) sits at the top.
* **The Face (Zone 2):** The HTML `return()` block sits at the bottom.
* **The Portal:** Use curly braces `{ }` to inject JavaScript variables directly into the HTML.

## 3. Props (The Template & The Ink)
* **The Problem:** We don't want 100 identical components. We want a blank template that can accept custom data.
* **The Solution:** We pass data into the component like an HTML attribute.
  * *Sending (Motherboard):* `<ProductCard name="Neon Mouse" price={49.99} />`
  * *Receiving (The Brick):* `function ProductCard(props) { return <h1>{props.name}</h1>; }`
* **Rule:** Strings get `" "`, Numbers and Variables get `{ }`.

## 4. The Conveyor Belt (.map)
* We rarely write components out by hand. Instead, we take an Array of data and `.map()` over it to build the UI automatically.
```jsx
{inventory.map(item => (
    <ProductCard key={item.id} name={item.name} />
))}