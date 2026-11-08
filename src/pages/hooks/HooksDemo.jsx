import Contador from '../../components/Contador';
import PerfilesEditables from '../../components/PerfilesEditables';
import Reloj from '../../components/Reloj';
import useOnlineStatus from '../../hooks/useOnlineStatus';

function StatusBar() {
    const online = useOnlineStatus();
    return <h3>{online ? 'Conectado' : 'Sin conexión'}</h3>;
}

function SaveButton() {
    const online = useOnlineStatus();
    return <button disabled={!online}>Guardar</button>;
}

export default function HooksDemo() {
    return (
        <main style={{ padding: '24px' }}>
            <h1>Hooks Demo</h1>

            <section>
                <h2>1. useState: contador</h2>
                <Contador />
            </section>

            <section>
                <h2>2. useState + useEffect: perfiles</h2>
                <PerfilesEditables />
            </section>

            <section>
                <h2>3. useEffect con cleanup: reloj</h2>
                <Reloj />
            </section>

            <section>
                <h2>4. Custom hook: useOnlineStatus</h2>
                <StatusBar />
                <SaveButton />
            </section>
        </main>
    );
}
