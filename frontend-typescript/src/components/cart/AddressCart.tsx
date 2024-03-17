import { Button, Card } from "@mui/material"
import HomeIcon from '@mui/icons-material/Home';

interface Props {
  item: number;
  showButton: boolean;
  handleSelectAddress: (item: number) => void;
}

const AddressCart = ({ item, showButton, handleSelectAddress }: Props) => {

  return (
    <Card className="flex gap-5 w-64 p-5">
      <HomeIcon />
      <div className="space-y-3 text-gray-500">
        <h1 className="font-semibold text-lg text-white">Home</h1>
        <p>13 Mulberry Lane, Havenwood</p>
        {
          showButton &&
          <Button
            onClick={() => handleSelectAddress(item)}
            variant="outlined"
            fullWidth
          >

            Select
          </Button>
        }
      </div>
    </Card>
  )
}

export default AddressCart