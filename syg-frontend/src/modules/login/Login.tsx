import React from 'react';
import 'Login.scss';

interface LoginProps {
  userName: string;
  password: string;
}

const Login: React.FC<LoginProps> = (props: LoginProps) => {
  return (
    <div>
      <h1>Hello, {props.userName}!</h1>
      <p>Welcome to my React TypeScript example.</p>
    </div>
  );
};

export default Login;