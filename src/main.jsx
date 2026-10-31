import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Sandbox from './pages/sandbox/Sandbox.jsx'

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <Sandbox />
    </StrictMode>,
)
