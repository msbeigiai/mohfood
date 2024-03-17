import { Box, Button, Card, Divider, Grid, Modal, TextField } from "@mui/material"
import CartItem from "./CartItem"
import AddressCart from "./AddressCart"
import AddLocationIcon from '@mui/icons-material/AddLocation';
import { useState } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from 'yup';

interface FormValue {
  streetAddress: string;
  state: string;
  pinCode: number;
  city: string;
}

const style = {
  position: 'absolute' as const,
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  boxShadow: 24,
  outline: "none",
  p: 4,
};

const initialValues = {
  streetAddress: "",
  state: "",
  pinCode: "",
  city: ""
}

const validationSchema = Yup.object({
  streetAddress: Yup.string().required("Street address is required."),
  state: Yup.string().required("State address is required."),
  pinCode: Yup.number().required("Pincode address is required."),
  city: Yup.string().required("City address is required."),
})

const items = [1, 1]

function Cart() {
  const [open, setOpen] = useState(false);
  const handleOpenAddressModal = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const createOrderUsingSelectedAddress = () => console.log("");
  const handleSubmit = (value: FormValue) => console.log("FORM Value", value);


  return (
    <div>
      <main className="lg:flex justify-between">
        <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
          {
            items.map(item => <CartItem />)
          }
          <Divider />
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Details</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                <p>$455</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Delivery Fee</p>
                <p>$22</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>GST & Restaurant Charges</p>
                <p>$11</p>
              </div>
              <Divider />
            </div>
            <div className="flex justify-between text-gray-400">
              <p>Total Payment</p>
              <p>$322</p>
            </div>
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">Coose Delivery Address</h1>
            <div className="flex gap-5 flex-wrap justify-center">
              {
                [1, 1, 1, 1, 1].map(item =>
                  <AddressCart
                    item={item}
                    showButton={true}
                    handleSelectAddress={createOrderUsingSelectedAddress}
                  />
                )
              }
              <Card className="flex gap-5 w-64 p-5">
                <AddLocationIcon />
                <div className="space-y-3 text-gray-500">
                  <h1 className="font-semibold text-lg text-white">Add New Address</h1>
                  <Button
                    onClick={() => handleOpenAddressModal()}
                    variant="outlined"
                    fullWidth
                  >
                    Add
                  </Button>
                </div>
              </Card>
            </div>
          </div>
        </section>
      </main>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Formik
            initialValues={initialValues}
            validationSchema={validationSchema}
            onSubmit={handleSubmit}
          >
            <Form>
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <Field
                    as={TextField}
                    name="streetAddress"
                    label="Street Address"
                    fullWidth
                    variant='outlined'
                    error={!ErrorMessage("streetAddress")}
                    helperText={
                      <ErrorMessage name="error">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={12}>
                  <Field
                    as={TextField}
                    name="state"
                    label="State"
                    fullWidth
                    variant='outlined'
                    error={!ErrorMessage("state")}
                    helperText={
                      <ErrorMessage name="error">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={12}>
                  <Field
                    as={TextField}
                    name="city"
                    label="City"
                    fullWidth
                    variant='outlined'
                    error={!ErrorMessage("city")}
                    helperText={
                      <ErrorMessage name="error">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={12}>
                  <Field
                    as={TextField}
                    name="pinCode"
                    label="Pincode"
                    fullWidth
                    variant='outlined'
                    error={!ErrorMessage("pinCode")}
                    helperText={
                      <ErrorMessage name="error">
                        {(msg) => <span className="text-red-600">{msg}</span>}
                      </ErrorMessage>
                    }
                  />
                </Grid>
                <Grid item xs={12}>
                  <Button
                    variant="contained"
                    type="submit"
                    color="primary"
                    fullWidth
                  >
                    Deliver Here
                  </Button>
                </Grid>
              </Grid>
            </Form>
          </Formik>
        </Box>
      </Modal>
    </div>
  )
}

export default Cart