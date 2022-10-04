/* eslint react/no-multi-comp: 0, react/prop-types: 0 */

import React from 'react';
import {
  Button, Modal, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label,
  Input, Alert
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
      errorMessages: '',
      successMessages: '',
      employee: null,
      haveChange: false
    };
    if (props.uuid) {
      employeeApi.get(props.uuid)
        .then(response => this.setState({ employee: response }))
    }
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
    if (this.props.uuid && this.state.haveChange) {
      employeeApi.update(this.props.uuid, {
        name: this.state.name ?? undefined,
        dept: this.state.dept ?? undefined,
        phone: this.state.phone ?? undefined,
        address: this.state.address ?? undefined
      })
        .then(res => {
          this.setState({
            employee: res,
            successMessages: res.message || 'Update employee success'
          });
        })
        .catch(err => {
          if (err.code === 400) {
            this.setState({ errorMessages: err.message });
          } else {
            alert(err.message);
            window.location.reload();
          }
        });
    } else { // create
      employeeApi.create(
        {
          name: this.state.name,
          dept: this.state.dept,
          phone: this.state.phone,
          address: this.state.address
        }
      )
        .then(res => {
          alert(res.message || 'Create employee success');
          this.toggle();
          this.props.refeshList();
        })
        .catch(err => {
          if (err.code === 400) {
            this.setState({ errorMessages: err.message });
          } else {
            alert(err.message);
            window.location.reload();
          }
        });
    }
  }

  handleChange = (event) => {
    const { target } = event;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const { name } = target;

    this.setState({
      [name]: value,
      errorMessages: '',
      successMessages: '',
      haveChange: true
    });
  }

  render() {
    return (
      <div>
        <Modal isOpen={this.state.modal} toggle={this.toggle} className={this.props.className}>
          <ModalHeader toggle={this.toggle}> {this.props.uuid ? "Update Employee: " + this.props.uuid : "Add new employee"}</ModalHeader>
          <ModalBody>
            {
              this.state.errorMessages &&
              <Alert color="danger">
                {this.state.errorMessages}
              </Alert>
            }
            {
              this.state.successMessages &&
              <Alert color="success">
                {this.state.successMessages}
              </Alert>
            }

            <Form name='updateEmployee'>
              <FormGroup>
                <Label for="exampleEmail">Name</Label>
                <Input type="text" name="name" placeholder="Name"
                  defaultValue={this.state.employee?.name}
                  onChange={(e) => this.handleChange(e)}
                  valid={this.state.name && this.state.successMessages !== ""}
                />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Dept</Label>
                <Input type="text" name="dept" placeholder="Dept"
                  defaultValue={this.state.employee?.dept}
                  onChange={(e) => this.handleChange(e)}
                  valid={this.state.dept && this.state.successMessages !== ""}
                />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Phone</Label>
                <Input type="text" name="phone" placeholder="Phone"
                  defaultValue={this.state.employee?.phone}
                  onChange={(e) => this.handleChange(e)}
                  valid={this.state.phone && this.state.successMessages !== ""}
                />
              </FormGroup>

              <FormGroup>
                <Label for="exampleEmail">Address</Label>
                <Input type="text" name="address" placeholder="Address"
                  defaultValue={this.state.employee?.address}
                  onChange={(e) => this.handleChange(e)}
                  valid={this.state.address && this.state.successMessages !== ""}
                />
              </FormGroup>
            </Form>
          </ModalBody>
          <ModalFooter>
            <Button color="primary" onClick={this.confirm} disabled={!this.state.haveChange}>Ok</Button>{' '}
            <Button color="secondary" onClick={this.toggle}>Cancel</Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
}

export default UpdateModel;