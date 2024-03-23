import { Card, CardContent, CardHeader, IconButton, SvgIconTypeMap } from '@mui/material';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import './UserInfoCard.scss';
import { ReactElement } from 'react';

interface UserInfoCardProps {
    title: string;
    info: string | number;
    iconItem?: ReactElement;
}

const UserInfoCard: React.FC<UserInfoCardProps> = (props: UserInfoCardProps) => {
    return (
        <Card id='syg-historic-card-container'>
            <CardHeader 
                id='syg-historic-card-header'
                title ={
                    <div id='syg-historic-card-header-content' >
                        {props.iconItem}
                        <span>{props.title}</span>
                    </div>
                }
            />
            <CardContent id='syg-historic-card-content'>
                <span>{props.info}</span>
            </CardContent>
        </Card>
    );
};

export default UserInfoCard;