import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from "@mui/material"
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { ChangeEvent, useState } from "react";
import MenuCard from "./MenuCard";

const categories = [
  "Appetizers",
  "Salads",
  "Soups & Stews",
  "Pasta",
  "Sandwiches & Wraps",
  "Curries & Stir-Fries",
  "Tacos & Burritos",
  "Noodles & Dumplings",
  "Breakfast",
  "Desserts",
  "Drinks"
]

const foodTypes = [
  { label: "All", value: "all" },
  { label: "Pescatarian(Focuses on Seafood)", value: "pescatarian" },
  { label: "Tridoshic(Based on Ayurvedic principles)", value: "trisoshic" },
  { label: "High - Protein", value: "highProtein" },
  { label: "Low - Carb", value: "lowCarb" },
  { label: "Paleo", value: "paleo" },
  { label: "Gluten - Free", value: "clutenFree" },
  { label: "Clean - Eating", value: "cleanEating" },
  { label: "Tex - Mex", value: "texMex" },
  { label: "Fusion", value: "fusion" },
  { label: "Decadent", value: "decadent" }
]

const menu = [1, 1, 1, 1, 1]

const RestaurantDetails = () => {
  const [foodType, setFoodType] = useState("all")
  const handleFilter = (e: ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value, e.target.name);
  }

  return (
    <div className="px-5 lg:px-20">
      <section>
        <h3 className="text-gray-500 py-2 mt-10">Home/Iran/Persian Fast Food/3</h3>
        <div>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <img
                src="https://cdn.pixabay.com/photo/2016/11/21/16/02/outdoor-dining-1846137_960_720.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                src="https://cdn.pixabay.com/photo/2019/09/12/15/21/resort-4471852_960_720.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
            <Grid item xs={12} lg={6}>
              <img
                src="https://cdn.pixabay.com/photo/2020/01/31/07/26/chef-4807317_1280.jpg"
                className="w-full h-[40vh] object-cover"
                alt=""
              />
            </Grid>
          </Grid>
        </div>
        <div className="pt-3 pb-5">
          <h1 className="text-4xl font-semibold">Persian Fast Food</h1>
          <p className="text-gray-500 mt-1">
            Welcome to The Wandering Fork, a culinary adventure unlike any other.
            We take you on a global gastronomic journey with a menu that celebrates fresh,
            seasonal ingredients and innovative dishes.
            From melt-in-your-mouth Seared Ahi Tuna with Wasabi Aioli to our comforting Classic
            Spaghetti Carbonara, our chefs craft every dish with passion and a keen eye for detail.
            Whether you're craving a perfectly cooked Pan-Seared Scottish Salmon or our decadent
            Decadent Chocolate Lava Cake, The Wandering Fork promises an unforgettable dining
            experience that will tantalize your taste buds and leave you wanting more.
          </p>
          <div className="space-y-3 mt-3">
            <p className="text-gray-400 flex items-center gap-3">
              <LocationOnIcon />
              <span>777 Coastal Highway, Pacific Bluffs</span>
            </p>
            <p className="text-gray-400 flex items-center gap-3">
              <CalendarTodayIcon />
              <span>Mon-SUN: 9:00 AM - 9:00 PM (Today)</span>
            </p>
          </div>
        </div>
      </section>
      <Divider />
      <section className="pt-[2rem] lg:flex relative">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28  p-5 shadow-md">
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Type
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup onChange={handleFilter} name="food_type" value={foodTypes}>
                  {foodTypes.map((item) =>
                    <FormControlLabel
                      key={item.value}
                      value={item.value}
                      control={<Radio />}
                      label={item.label}
                    />)}
                </RadioGroup>
              </FormControl>
            </div>
            <Divider />
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Food Category
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup onChange={handleFilter} name="food_type" value={foodTypes}>
                  {categories.map((item) =>
                    <FormControlLabel
                      key={item}
                      value={item}
                      control={<Radio />}
                      label={item}
                    />)}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>
        <div className="space-y-5 lg:w-[80%] lg:pl-10">
          {
            menu.map((item) =>
              <MenuCard />
            )
          }
        </div>
      </section>
    </div>
  )
}

export default RestaurantDetails