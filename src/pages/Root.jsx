import { Outlet, NavLink } from 'react-router';

export default function Root() {
    return (
        <>
            <nav style={{ display: 'flex', gap: '16px', padding: '16px' }}>
                <NavLink to="/">Inicio</NavLink>
                <NavLink to="/profiles">Perfiles</NavLink>
                <NavLink to="/sandbox">Sandbox</NavLink>
                <NavLink to="/hooks">Hooks</NavLink>
                <NavLink to="/signin">Ingresar</NavLink>
                <NavLink to="/signup">Registro</NavLink>
            </nav>

            <Outlet />
        </>
    );
}
