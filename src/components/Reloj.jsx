import { useState, useEffect } from 'react';

export default function Reloj() {
    const [now, setNow] = useState(new Date());

    useEffect(() => {
        const id = setInterval(() => setNow(new Date()), 1000);
        return () => clearInterval(id);
    }, []);

    return <p>{now.toLocaleTimeString()}</p>;
}
