import { DeviceStatus } from 'app/shared/model/enumerations/device-status.model';

export interface IDevice {
  id?: number;
  name?: string;
  model?: string;
  portNumber?: number;
  emulatorName?: string;
  status?: DeviceStatus;
}

export class Device implements IDevice {
  constructor(
    public id?: number,
    public name?: string,
    public model?: string,
    public portNumber?: number,
    public emulatorName?: string,
    public status?: DeviceStatus
  ) {}
}
