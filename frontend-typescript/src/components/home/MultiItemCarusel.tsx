import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick';
import { topMeals } from "./TopMeal";
import CarouselItem from "./CarouselItem";


const MultiItemCarusel = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: false
  };
  return (
    <div>
      <Slider {...settings}>
        {
          topMeals.map((meal, index) => <CarouselItem key={index} image={meal.image} title={meal.title} />)
        }
      </Slider>
    </div>
  )
}

export default MultiItemCarusel;