import Button from '../../components/Button';

export default function SignUp() {
    const handleSubmit = (e) => {
        e.preventDefault();
        const data = Object.fromEntries(new FormData(e.target));
        console.log('SignUp data:', data);
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Sign Up</h2>
            <input name="name" type="text" placeholder="Nombre" required />
            <input name="email" type="email" placeholder="Correo" required />
            <input name="password" type="password" placeholder="Contraseña" required />
            <Button type="submit">Registrarse</Button>
        </form>
    );
}
