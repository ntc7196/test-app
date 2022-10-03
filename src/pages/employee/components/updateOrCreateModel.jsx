/* eslint react/no-multi-comp: 0, react/prop-types: 0 */

import React from 'react';
import {
  Button, Modal, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label,
  Input
} from 'reactstrap';
import employeeApi from "../../../api/employeeApi";

class UpdateModel extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      modal: true,
      name: '',
      dept: '',
      phone: '',
      address: '',
    };

    this.toggle = this.toggle.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.confirm = this.confirm.bind(this);
  }
  toggle() {
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
    this.props.onClose()
  }
  confirm() {
    // update
    if (this.props.data?.uuid) {

      employeeApi.update(this.props.data?.uuid, {
        name: this.state.name ? this.state.name : this.props.data.name,
        dept: this.state.dept ? this.state.dept : this.props.data.dept,
        phone: this.state.phone ? this.state.phone : this.props.data.phone,
        address: this.state.address ? this.state.address : this.props.data.address
      })
    } else { // create
      employeeApi.create(
        {
          name: this.state.name,
          dept: this.state.dept,
          phone: this.state.phone,
          address: this.state.address
        }
      )
    }
  }

  handleChange = (event) => {
    const { target } = event;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const { name } = target;

    this.setState({
      [name]: value,
    });
  }

  render() {
    return (
      <div>
        <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}> {this.props.data?.uuid ? "Update Employee:" + this.props.data.uuid : "Add new employee"}</ModalHeader>
          <ModalBody>
            <Form name='updateEmployee'>
              <FormGroup>
                <Label for="exampleEmail">Name</Label>
                <Input type="text" name="name" placeholder="Name"
                  defaultValue={this.props.data?.name}
                  onChange={(e) => this.handleChange(e)} />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Dept</Label>
                <Input type="text" name="dept" placeholder="Dept"
                  defaultValue={this.props.data?.dept}
                  onChange={(e) => this.handleChange(e)} />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Phone</Label>
                <Input type="text" name="phone" placeholder="Phone"
                  defaultValue={this.props.data?.phone}
                  onChange={(e) => this.handleChange(e)} />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Address</Label>
                <Input type="text" name="address" placeholder="Address"
                  defaultValue={this.props.data?.address}
                  onChange={(e) => this.handleChange(e)} />
              </FormGroup>
            </Form>
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.confirm}>Ok</Button>{' '}
            <Button color="secondary" onClick={this.toggle}>Cancel</Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
}

export default UpdateModel;