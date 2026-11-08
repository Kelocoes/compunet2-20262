import { useParams, useNavigate } from 'react-router';
import Profile from '../../components/Profile';
import Button from '../../components/Button';
import { profiles } from '../../data/profiles';

export default function ProfileDetail() {
    const { id } = useParams(); // siempre es texto
    const navigate = useNavigate();
    const profile = profiles.find((p) => p.id === Number(id));

    return (
        <main style={{ padding: '24px' }}>
            <Button onClick={() => navigate(-1)}>Volver</Button>
            {profile ? (
                <Profile name={profile.name} imageId={profile.imageId} width={300} height={400} />
            ) : (
                <p>No existe el perfil {id}</p>
            )}
        </main>
    );
}
