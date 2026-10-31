export default function Profile({
    name = 'Anonymous',
    imageId = 64,
    width = 150,
    height = 200,
}) {
    return (
        <figure style={{ margin: '10px', textAlign: 'center' }}>
            <img
                src={`https://picsum.photos/id/${imageId}/${width}/${height}`}
                alt={name}
                style={{ borderRadius: '8px', objectFit: 'cover' }}
            />
            <figcaption>{name}</figcaption>
        </figure>
    );
}
