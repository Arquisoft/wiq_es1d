import React from 'react';
import { Card, CardContent, CardHeader } from '@mui/material';
import { ReactElement } from 'react';
import './UserInfoCard.scss';

interface UserInfoCardProps {
    title: string;
    info: string | number;
    iconItem?: ReactElement;
}

const UserInfoCard: React.FC<UserInfoCardProps> = (props: UserInfoCardProps) => {
    return (
        <Card id='syg-historic-card-container' data-testid='syg-historic-card-container'>
            <CardHeader 
                id='syg-historic-card-header'
                data-testid='syg-historic-card-header'
                title ={
                    <div id='syg-historic-card-header-content' data-testid='syg-historic-card-header-content'>
                        {props.iconItem}
                        <span data-testid='syg-historic-card-header-title'>{props.title}</span>
                    </div>
                }
            />
            <CardContent id='syg-historic-card-content' data-testid='syg-historic-card-content'>
                <span data-testid='syg-historic-card-content-info'>{props.info}</span>
            </CardContent>
        </Card>
    );
};

export default UserInfoCard;