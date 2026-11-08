import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import HooksDemo from './pages/hooks/HooksDemo.jsx'

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <HooksDemo />
    </StrictMode>,
)
