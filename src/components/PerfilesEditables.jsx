import { useState, useEffect } from 'react';
import ListProfiles from './ListProfiles';

const iniciales = [
    { id: 1, name: 'Katherine Johnson', imageId: 1025 },
    { id: 2, name: 'Alan Turing', imageId: 1062 },
];

export default function PerfilesEditables() {
    const [profiles, setProfiles] = useState(iniciales);
    const [name, setName] = useState('');

    useEffect(() => {
        document.title = `${profiles.length} perfiles`;
    }, [profiles.length]);

    const agregar = (e) => {
        e.preventDefault();
        if (!name.trim()) return;
        setProfiles([...profiles, { id: Date.now(), name, imageId: Math.floor(Math.random() * 100) }]);
        setName('');
    };

    const quitarUltimo = () => setProfiles(profiles.slice(0, -1));

    return (
        <div>
            <form onSubmit={agregar}>
                <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Nombre" />
                <button type="submit">Agregar</button>
                <button type="button" onClick={quitarUltimo}>Quitar último</button>
            </form>
            <ListProfiles profiles={profiles} />
        </div>
    );
}
