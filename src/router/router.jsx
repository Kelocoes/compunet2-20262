import { createBrowserRouter } from 'react-router';
import Root from '../pages/Root';
import Home from '../pages/home/Home';
import HooksDemo from '../pages/hooks/HooksDemo';
import SignIn from '../pages/signIn/SignIn';
import SignUp from '../pages/signUp/SignUp';
import ProfilesPage from '../pages/profiles/ProfilesPage';
import ProfileDetail from '../pages/profiles/ProfileDetail';
import Sandbox from '../pages/sandbox/Sandbox';

export const router = createBrowserRouter([
    {
        path: '/',
        Component: Root,
        children: [
            { index: true, Component: Home },
            { path: 'sandbox', Component: Sandbox },
            { path: 'hooks', Component: HooksDemo },
            { path: 'signin', Component: SignIn },
            { path: 'signup', Component: SignUp },
            {
                path: 'profiles',
                children: [
                    { index: true, Component: ProfilesPage },
                    { path: ':id', Component: ProfileDetail },
                ],
            },
        ],
    },
]);
