import { Accordion, AccordionDetails, AccordionSummary, Button, Checkbox, FormControlLabel, FormGroup } from "@mui/material"
import ExpandMoreIcon from '@mui/icons-material/ExpandMore'

const demo = [
  { category: "Nuts & Seeds", ingredients: ["Cashews"] },
  { category: "Protein", ingredients: ["Cashews", "Bacon strips"] },
]

const handleCheckboxChange = (ingredient: string) => console.log(ingredient);


const MenuCard = () => {
  return (
    <div>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <div className="lg:flex items-center justify-between">
            <div className="lg:flex item-center lg:gap-5">
              <img
                src="https://cdn.pixabay.com/photo/2016/03/27/21/34/restaurant-1284351_1280.jpg"
                alt=""
                className="w-[7rem] h-[7rem] object-cover"
              />
              <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
                <p className="font-semibold text-xl ">Burger</p>
                <p>499$</p>
                <p className="text-gray-400">nice food in this restaurant.</p>
              </div>
            </div>
          </div>
        </AccordionSummary>
        <AccordionDetails>
          <form>
            <div className="flex gap-5 flex-wrap">
              {
                demo.map(item => (
                  <div>
                    <p>{item.category}</p>
                    <FormGroup>
                      {
                        item.ingredients.map(
                          ingredient => <FormControlLabel control={<Checkbox onChange={() => handleCheckboxChange(ingredient)} />} label={ingredient} />
                        )
                      }
                    </FormGroup>
                  </div>
                ))
              }
            </div>
            <div className="pt-5">
              <Button type="submit" variant="contained" disabled={false}>
                {true ? "Add to Cart" : "Out of Stock"}
              </Button>
            </div>
          </form>
        </AccordionDetails>
      </Accordion>
    </div>
  )
}

export default MenuCard