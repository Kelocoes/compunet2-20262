import { useNavigate } from 'react-router';
import Button from '../../components/Button';

export default function SignIn() {
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = Object.fromEntries(new FormData(e.target));
        console.log('SignIn data:', data);
        // replace: Atrás no vuelve al formulario de login
        navigate('/', { replace: true });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Sign In</h2>
            <input name="email" type="email" placeholder="Correo" required />
            <input name="password" type="password" placeholder="Contraseña" required />
            <Button type="submit">Ingresar</Button>
        </form>
    );
}
