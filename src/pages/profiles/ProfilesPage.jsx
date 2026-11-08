import { Link } from 'react-router';
import Profile from '../../components/Profile';
import { profiles } from '../../data/profiles';

export default function ProfilesPage() {
    return (
        <main style={{ padding: '24px' }}>
            <h1>Perfiles</h1>
            <div style={{ display: 'flex', gap: '16px', flexWrap: 'wrap' }}>
                {profiles.map((p) => (
                    <Link key={p.id} to={`/profiles/${p.id}`}>
                        <Profile name={p.name} imageId={p.imageId} />
                    </Link>
                ))}
            </div>
        </main>
    );
}
