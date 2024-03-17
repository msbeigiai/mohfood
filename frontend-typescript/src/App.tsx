import { darkTheme } from "./theme/DarkTheme"
import { ThemeProvider, CssBaseline } from "@mui/material"
import Navbar from './components/navbar/Navbar'
import Home from "./components/home/Home"
import RestaurantDetails from "./components/restaurant/RestaurantDetails"
import Cart from "./components/cart/Cart"
import Profile from "./components/profile/Profile"

function App() {

  return (
    <>
      <ThemeProvider theme={darkTheme} >
        <CssBaseline />
        <Navbar />
        {/* <Home /> */}
        {/* <RestaurantDetails /> */}
        {/* <Cart /> */}
        <Profile />
      </ThemeProvider>
    </>
  )
}

export default App
