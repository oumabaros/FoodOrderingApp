import {NextFunction, Request, Response} from 'express';
import {body, validationResult} from 'express-validator';

const handleValidationErrors = async (
  req: Request,
  res: Response,
  next: NextFunction,
): Promise<any> => {
  const errors = validationResult(req);

  if (!errors.isEmpty()) {
    return res.status(400).json({errors: errors.array()});
  }
  next();
};

export const validateMyUserRequest = [
  body('name').isString().notEmpty().withMessage('Name is required'),
  body('addressLine1')
    .isString()
    .notEmpty()
    .withMessage('Address Line1 is required'),
  body('city').isString().notEmpty().withMessage('City is required'),
  body('country').isString().notEmpty().withMessage('Country is required'),
  handleValidationErrors,
];

export const validateMyRestaurantRequest = [
  body('restaurantName').notEmpty().withMessage('Restaurant Name is required'),
  body('city').notEmpty().withMessage('City is required'),
  body('country').notEmpty().withMessage('Country is required'),
  body('cuisines')
    .isArray()
    .withMessage('Cusines must be an array')
    .not()
    .isEmpty()
    .withMessage('Cuisines array cannot be empty'),
  body('deliveryPrice')
    .isFloat({min: 0})
    .withMessage('Delivery Price must be a positive number'),
  body('estimatedDeliveryTime')
    .isInt({min: 0})
    .withMessage('Delivery time must be a positive integer'),
  body('menuItems').isArray().withMessage('Menu Items must be an array'),
  body('menuItems.*.name').notEmpty().withMessage('Menu item name is required'),
  body('menuItems.*.price')
    .isFloat({min: 0})
    .withMessage('Menu item price is required and must be a positive number'),
  handleValidationErrors,
];
