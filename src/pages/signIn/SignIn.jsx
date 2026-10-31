import Button from '../../components/Button';

export default function SignIn() {
    const handleSubmit = (e) => {
        e.preventDefault();
        const data = Object.fromEntries(new FormData(e.target));
        console.log('SignIn data:', data);
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
