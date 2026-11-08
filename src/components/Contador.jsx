import { useState } from 'react';

export default function Contador() {
    const [count, setCount] = useState(0);

    return (
        <div>
            <button onClick={() => setCount(count + 1)}>Clics: {count}</button>
            <button onClick={() => { setCount(count + 1); setCount(count + 1); setCount(count + 1); }}>
                +3 (con valor)
            </button>
            <button onClick={() => { setCount((c) => c + 1); setCount((c) => c + 1); setCount((c) => c + 1); }}>
                +3 (con función)
            </button>
        </div>
    );
}
