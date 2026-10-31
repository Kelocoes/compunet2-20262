import Profile from './Profile';

const defaultProfiles = [
    { id: 1, name: 'Katherine Johnson', imageId: 1025 },
    { id: 2, name: 'Alan Turing', imageId: 1062 },
    { id: 3, name: 'Ada Lovelace', imageId: 1069 },
];

export default function ListProfiles({ profiles = defaultProfiles }) {
    return (
        <div style={{ display: 'flex', gap: '16px', flexWrap: 'wrap' }}>
            {profiles.map((item) => (
                <Profile
                    key={item.id}
                    name={item.name}
                    imageId={item.imageId}
                    width={150}
                    height={200}
                />
            ))}
        </div>
    );
}
